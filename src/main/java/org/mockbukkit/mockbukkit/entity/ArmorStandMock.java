package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import io.papermc.paper.math.Rotations;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.data.EntitySubType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Mock implementation of an {@link ArmorStand}.
 *
 * @see LivingEntityMock
 */
public class ArmorStandMock extends LivingEntityMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.ArmorStandBaseMock
{

	private static final String POSE_NULL = "Pose cannot be null";
	private static final String SLOT_NULL = "Slot cannot be null";
	private static final String SLOTS_NULL = "Slots cannot be null";
	private static final String ROTATIONS_NULL = "Rotations cannot be null";

	private boolean hasArms = false;

	private boolean isSmall = false;

	private boolean isMarker = false;

	private boolean hasBasePlate = true;

	private boolean isVisible = true;

	private boolean isMovable = true;

	private boolean isTickable = true;

	@NotNull
	private EulerAngle headPose = EulerAngle.ZERO;

	@NotNull
	private EulerAngle bodyPose = EulerAngle.ZERO;

	@NotNull
	private EulerAngle leftArmPose = new EulerAngle(Math.toRadians(-10.0f), 0.0f, Math.toRadians(-10.0f));

	@NotNull
	private EulerAngle rightArmPose = new EulerAngle(Math.toRadians(-15.0f), 0.0f, Math.toRadians(10.0f));

	@NotNull
	private EulerAngle leftLegPose = new EulerAngle(Math.toRadians(-1.0f), 0.0f, Math.toRadians(-1.0f));

	@NotNull
	private EulerAngle rightLegPose = new EulerAngle(Math.toRadians(1.0f), 0.0f, Math.toRadians(1.0f));

	private final Set<EquipmentSlot> disabledSlots = EnumSet.noneOf(EquipmentSlot.class);

	/**
	 * Constructs a new {@link ArmorStandMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ArmorStandMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ARMOR_STAND;
	}

	@Override
	protected EntitySubType getSubType()
	{
		if (isSmall())
		{
			return EntitySubType.SMALL;
		}
		return super.getSubType();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	@NotNull
	public ItemStack getBoots()
	{
		return getEquipment().getBoots();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	public void setBoots(@Nullable ItemStack item)
	{
		getEquipment().setBoots(item);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	@NotNull
	public ItemStack getLeggings()
	{
		return getEquipment().getLeggings();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	public void setLeggings(@Nullable ItemStack item)
	{
		getEquipment().setLeggings(item);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	@NotNull
	public ItemStack getChestplate()
	{
		return getEquipment().getChestplate();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	public void setChestplate(@Nullable ItemStack item)
	{
		getEquipment().setChestplate(item);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	@NotNull
	public ItemStack getHelmet()
	{
		return getEquipment().getHelmet();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	public void setHelmet(@Nullable ItemStack item)
	{
		getEquipment().setHelmet(item);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	@NotNull
	public ItemStack getItemInHand()
	{
		return getEquipment().getItemInMainHand();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.15.2")
	public void setItemInHand(@Nullable ItemStack item)
	{
		getEquipment().setItemInMainHand(item);
	}

	@Override
	@NotNull
	public EulerAngle getBodyPose()
	{
		return bodyPose;
	}

	@Override
	public void setBodyPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.bodyPose = pose;
	}

	@Override
	@NotNull
	public EulerAngle getLeftArmPose()
	{
		return leftArmPose;
	}

	@Override
	public void setLeftArmPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.leftArmPose = pose;
	}

	@Override
	@NotNull
	public EulerAngle getRightArmPose()
	{
		return rightArmPose;
	}

	@Override
	public void setRightArmPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.rightArmPose = pose;
	}

	@Override
	@NotNull
	public EulerAngle getLeftLegPose()
	{
		return leftLegPose;
	}

	@Override
	public void setLeftLegPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.leftLegPose = pose;
	}

	@Override
	@NotNull
	public EulerAngle getRightLegPose()
	{
		return rightLegPose;
	}

	@Override
	public void setRightLegPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.rightLegPose = pose;
	}

	@Override
	@NotNull
	public EulerAngle getHeadPose()
	{
		return headPose;
	}

	@Override
	public void setHeadPose(@NotNull EulerAngle pose)
	{
		Preconditions.checkNotNull(pose, POSE_NULL);
		this.headPose = pose;
	}

	@Override
	public boolean hasBasePlate()
	{
		return hasBasePlate;
	}

	@Override
	public void setBasePlate(boolean basePlate)
	{
		this.hasBasePlate = basePlate;
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	public boolean isVisible()
	{
		return isVisible;
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.isVisible = visible;
	}

	@Override
	public boolean hasArms()
	{
		return hasArms;
	}

	@Override
	public void setArms(boolean arms)
	{
		this.hasArms = arms;
	}

	@Override
	public boolean isSmall()
	{
		return isSmall;
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	public void setSmall(boolean small)
	{
		this.isSmall = small;
	}

	@Override
	public boolean isMarker()
	{
		return isMarker;
	}

	@Override
	public void setMarker(boolean marker)
	{
		this.isMarker = marker;
	}

	@Override
	public boolean canMove()
	{
		return this.isMovable;
	}

	@Override
	public void setCanMove(boolean canMove)
	{
		this.isMovable = canMove;
	}

	@Override
	public boolean canTick()
	{
		return this.isTickable;
	}

	@Override
	public void setCanTick(boolean tick)
	{
		this.isTickable = tick;
	}

	@Override
	@NotNull
	public ItemStack getItem(@NotNull EquipmentSlot slot)
	{
		Preconditions.checkNotNull(slot, SLOT_NULL);
		return getEquipment().getItem(slot);
	}

	@Override
	public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item)
	{
		Preconditions.checkNotNull(slot, SLOT_NULL);
		getEquipment().setItem(slot, item);
	}

	@Override
	@NotNull
	public Set<EquipmentSlot> getDisabledSlots()
	{
		return EnumSet.copyOf(this.disabledSlots);
	}

	@Override
	public void setDisabledSlots(@NotNull EquipmentSlot... slots)
	{
		Preconditions.checkNotNull(slots, SLOTS_NULL);
		this.disabledSlots.clear();
		Collections.addAll(this.disabledSlots, slots);
	}

	@Override
	public void addDisabledSlots(@NotNull EquipmentSlot... slots)
	{
		Preconditions.checkNotNull(slots, SLOTS_NULL);
		Collections.addAll(this.disabledSlots, slots);
	}

	@Override
	public void removeDisabledSlots(@NotNull EquipmentSlot... slots)
	{
		Preconditions.checkNotNull(slots, SLOTS_NULL);
		List.of(slots).forEach(this.disabledSlots::remove);
	}

	@Override
	public boolean isSlotDisabled(@NotNull EquipmentSlot slot)
	{
		Preconditions.checkNotNull(slot, SLOT_NULL);
		return this.disabledSlots.contains(slot);
	}

	@Override
	@NotNull
	public Rotations getBodyRotations()
	{
		return toPaper(getBodyPose());
	}

	@Override
	public void setBodyRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setBodyPose(toBukkit(rotations));
	}

	@Override
	@NotNull
	public Rotations getLeftArmRotations()
	{
		return toPaper(getLeftArmPose());
	}

	@Override
	public void setLeftArmRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setLeftArmPose(toBukkit(rotations));
	}

	@Override
	@NotNull
	public Rotations getRightArmRotations()
	{
		return toPaper(getRightArmPose());
	}

	@Override
	public void setRightArmRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setRightArmPose(toBukkit(rotations));
	}

	@Override
	@NotNull
	public Rotations getLeftLegRotations()
	{
		return toPaper(getLeftLegPose());
	}

	@Override
	public void setLeftLegRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setLeftLegPose(toBukkit(rotations));
	}

	@Override
	@NotNull
	public Rotations getRightLegRotations()
	{
		return toPaper(getRightLegPose());
	}

	@Override
	public void setRightLegRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setRightLegPose(toBukkit(rotations));
	}

	@Override
	@NotNull
	public Rotations getHeadRotations()
	{
		return toPaper(getHeadPose());
	}

	@Override
	public void setHeadRotations(@NotNull Rotations rotations)
	{
		Preconditions.checkNotNull(rotations, ROTATIONS_NULL);
		setHeadPose(toBukkit(rotations));
	}

	private static EulerAngle toBukkit(Rotations rotation)
	{
		return new EulerAngle(Math.toRadians(rotation.x()), Math.toRadians(rotation.y()), Math.toRadians(rotation.z()));
	}

	private static Rotations toPaper(EulerAngle eulerAngle)
	{
		return Rotations.ofDegrees(Math.toDegrees(eulerAngle.getX()), Math.toDegrees(eulerAngle.getY()),
				Math.toDegrees(eulerAngle.getZ()));
	}
}
